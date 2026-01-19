import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UsuarioProyectoService } from '../../../services/usuario-proyecto.service';
import { HorasService } from '../../../services/horas.service';
import { LoginService } from '../../../services/login.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-registro-horas',
  templateUrl: './registro-horas.component.html',
  styleUrls: ['./registro-horas.component.css']
})
export class RegistroHorasComponent implements OnInit {
  proyectos: any[] = [];
  registros: any[] = [];
  tiposHora = ['ORDINARIAS', 'EXTRAS', 'FESTIVAS'];
  registroForm: FormGroup;
  formularioVisible: boolean = false;
  accion: string = '';

  displayedColumns: string[] = ['proyecto', 'fechaInicio', 'numeroHoras', 'tipoHoras', 'acciones'];

  constructor(
    private fb: FormBuilder,
    private usuarioProyectoService: UsuarioProyectoService,
    private horasService: HorasService,
    private loginService: LoginService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {
    this.registroForm = this.fb.group({
      id: [''],
      proyectoId: [''],
      fechaInicio: [''],
      numeroHoras: [0],
      tipoHoras: ['']
    });
  }

  ngOnInit(): void {
    this.cargarProyectos();
  }

  cargarProyectos(): void {
    const usuario = this.loginService.getUser();
    this.usuarioProyectoService.obtenerProyectosPorUsuario(usuario.id).subscribe((proyectos: any[]) => {
      this.proyectos = proyectos.map(proyecto => proyecto.proyecto);
      this.cargarRegistros();
    });
  }

  cargarRegistros(): void {
    const usuario = this.loginService.getUser();
    this.horasService.obtenerHorasPorUsuario(usuario.id).subscribe((registros: any[]) => {
      this.registros = registros.map(registro => {
        const proyecto = this.proyectos.find(p => p.idProyecto === registro.proyecto.idProyecto);
        return { ...registro, proyecto };
      });
    });
  }

  mostrarFormulario(accion: string, registro?: any): void {
    this.formularioVisible = true;
    this.accion = accion;
    if (accion === 'editar') {
      this.registroForm.patchValue({
        id: registro.id,
        proyectoId: registro.proyecto.idProyecto,
        fechaInicio: registro.fechaInicio,
        numeroHoras: registro.numeroHoras,
        tipoHoras: registro.tipoHoras
      });
    } else {
      this.registroForm.reset();
    }
  }

  submitForm(): void {
    const usuario = this.loginService.getUser();
    const registroData = {
      id: this.registroForm.get('id')?.value,
      usuario: { id: usuario.id },
      proyecto: { idProyecto: this.registroForm.get('proyectoId')?.value },
      fechaInicio: this.registroForm.get('fechaInicio')?.value,
      numeroHoras: this.registroForm.get('numeroHoras')?.value,
      tipoHoras: this.registroForm.get('tipoHoras')?.value
    };

    if (this.accion === 'add') {
      this.horasService.guardarRegistro(registroData).subscribe((nuevoRegistro) => {
        const proyecto = this.proyectos.find(p => p.idProyecto === nuevoRegistro.proyecto.idProyecto);
        this.registros = [...this.registros, { ...nuevoRegistro, proyecto }];
        this.formularioVisible = false;
        this.snackBar.open('Registro añadido con éxito', 'Aceptar', { duration: 3000 });
      }, error => {
        this.snackBar.open('Error al guardar el registro', 'Aceptar', { duration: 3000 });
      });
    } else if (this.accion === 'editar') {
      this.horasService.actualizarRegistro(registroData).subscribe((registroActualizado) => {
        const index = this.registros.findIndex(r => r.id === registroActualizado.id);
        if (index !== -1) {
          const proyecto = this.proyectos.find(p => p.idProyecto === registroActualizado.proyecto.idProyecto);
          this.registros[index] = { ...registroActualizado, proyecto };
          this.registros = [...this.registros];
        }
        this.formularioVisible = false;
        this.snackBar.open('Registro editado con éxito', 'Aceptar', { duration: 3000 });
      }, error => {
        this.snackBar.open('Error al actualizar el registro', 'Aceptar', { duration: 3000 });
      });
    }
  }

  cancelar(): void {
    this.formularioVisible = false;
  }

  eliminarRegistro(registroId: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '300px',
      data: { message: '¿Está seguro de que desea eliminar este registro?' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.horasService.eliminarRegistro(registroId).subscribe(() => {
          this.registros = this.registros.filter(r => r.id !== registroId);
          this.snackBar.open('Registro eliminado con éxito', 'Aceptar', { duration: 3000 });
        });
      }
    });
  }
}