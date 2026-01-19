import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UsuarioService } from '../../../services/usuario.service';
import { ProyectoService } from '../../../services/proyecto.service';
import { UsuarioProyectoService } from '../../../services/usuario-proyecto.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {
  usuarios: any[] = [];
  proyectos: any[] = [];
  proyectosAsignados: any[] = [];
  mostrarProyectos: boolean = false;
  usuarioForm: FormGroup;
  formularioVisible: boolean = false;
  accion: string = '';
  selectedFile: File | null = null;

  displayedColumns: string[] = ['nombre', 'apellido', 'email', 'acciones'];

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private proyectoService: ProyectoService,
    private usuarioProyectoService: UsuarioProyectoService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {
    this.usuarioForm = this.fb.group({
      id: [''],
      username: [''],
      password: [''],
      nombre: [''],
      apellido: [''],
      email: [''],
      rol: ['NORMAL'],
      proyectoId: ['']
    });
  }

  ngOnInit(): void {
    this.cargarUsuarios();
    this.cargarProyectos();
  }

  cargarUsuarios(): void {
    this.usuarioService.obtenerUsuarios().subscribe((usuarios: any[]) => {
      this.usuarios = usuarios;
    });
  }

  cargarProyectos(): void {
    this.proyectoService.obtenerProyectos().subscribe((proyectos: any[]) => {
      this.proyectos = proyectos;
    });
  }

  mostrarFormulario(accion: string, usuario?: any): void {
    this.formularioVisible = true;
    this.accion = accion;
    this.selectedFile = null;
    if (accion === 'editar' || accion === 'asignarProyecto') {
      this.usuarioForm.patchValue({
        id: usuario.id,
        username: usuario.username,
        password: usuario.password,
        nombre: usuario.nombre,
        apellido: usuario.apellido,
        email: usuario.email,
        rol: usuario.rol,
        proyectoId: ''
      });
    } else {
      this.usuarioForm.reset({ rol: 'NORMAL' });
    }
  }
  onFileSelected(event: any): void { this.selectedFile = event.target.files[0]; }

  submitForm(): void {
    if (this.accion === 'add') {
      const formData = new FormData();
      formData.append(
        "usuario",
        new Blob([JSON.stringify(this.usuarioForm.value)], { type: "application/json" }));
      if (this.selectedFile) {
        formData.append("file", this.selectedFile);
      }
      this.usuarioService.agregarUsuario(formData).subscribe(() => {
        this.cargarUsuarios();
        this.formularioVisible = false;
        this.snackBar.open('Usuario añadido con éxito', 'Aceptar', { duration: 3000 });
      });
    } else if (this.accion === 'editar') {

      const formData = new FormData();

      formData.append(
        "usuario",
        new Blob([JSON.stringify(this.usuarioForm.value)], { type: "application/json" })
      );

      if (this.selectedFile) {
        formData.append("file", this.selectedFile);
      }

      this.usuarioService.editarUsuario(this.usuarioForm.value.id, formData).subscribe(() => {
        this.cargarUsuarios();
        this.formularioVisible = false;
        this.snackBar.open('Usuario editado con éxito', 'Aceptar', { duration: 3000 });
      });
    } else if (this.accion === 'asignarProyecto') {
      const asignacion = {
        usuario: { id: this.usuarioForm.get('id')?.value },
        proyecto: { idProyecto: this.usuarioForm.get('proyectoId')?.value }
      };
      this.usuarioProyectoService.asignarProyecto(asignacion).subscribe(() => {
        this.cargarUsuarios();
        this.formularioVisible = false;
        this.snackBar.open('Proyecto asignado con éxito', 'Aceptar', { duration: 3000 });
      }, error => {
        this.snackBar.open('Error al asignar el proyecto', 'Aceptar', { duration: 3000 });
      });
    }
  }

  cancelar(): void {
    this.formularioVisible = false;
  }

  eliminarUsuario(usuarioId: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '250px',
      data: { message: '¿Está seguro de que desea eliminar este usuario?' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.usuarioService.eliminarUsuario(usuarioId).subscribe(() => {
          this.cargarUsuarios();
          this.snackBar.open('Usuario eliminado con éxito', 'Aceptar', { duration: 3000 });
        }, error => {
          this.snackBar.open('Error al eliminar el usuario', 'Aceptar', { duration: 3000 });
        });
      }
    });
  }



  verProyectos(usuario: any): void {
    this.usuarioProyectoService.obtenerProyectosPorUsuario(usuario.id).subscribe((proyectos: any[]) => {
      this.proyectosAsignados = proyectos;
      this.mostrarProyectos = true;
    });
  }

  cerrarProyectosAsignados(): void {
    this.proyectosAsignados = [];
    this.mostrarProyectos = false;
  }
}
