import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ProyectoService } from '../../../services/proyecto.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-proyectos',
  templateUrl: './proyectos.component.html',
  styleUrls: ['./proyectos.component.css']
})
export class ProyectosComponent implements OnInit {
  proyectos: any[] = [];
  proyectoForm: FormGroup;
  formularioVisible: boolean = false;
  accion: string = '';
  displayedColumns: string[] = ['idProyecto', 'nombreProyecto', 'acciones'];

  constructor(
    private fb: FormBuilder,
    private proyectoService: ProyectoService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {
    this.proyectoForm = this.fb.group({
      idProyecto: [''],
      nombreProyecto: [''],
      descripcionProyecto: ['']
    });
  }

  ngOnInit(): void {
    this.cargarProyectos();
  }

  cargarProyectos(): void {
    this.proyectoService.obtenerProyectos().subscribe((proyectos: any[]) => {
      this.proyectos = proyectos;
    });
  }

  mostrarFormulario(accion: string, proyecto?: any): void {
    this.formularioVisible = true;
    this.accion = accion;
    if (accion === 'editar' || accion === 'verDetalles') {
      this.proyectoForm.patchValue(proyecto);
    } else {
      this.proyectoForm.reset();
    }
  }

  submitForm(): void {
    if (this.accion === 'add') {
      this.proyectoService.agregarProyecto(this.proyectoForm.value).subscribe(() => {
        this.cargarProyectos();
        this.formularioVisible = false;
        this.snackBar.open('Proyecto añadido con éxito', 'Aceptar', { duration: 3000 });
      });
    } else if (this.accion === 'editar') {
      this.proyectoService.editarProyecto(this.proyectoForm.value).subscribe(() => {
        this.cargarProyectos();
        this.formularioVisible = false;
        this.snackBar.open('Proyecto editado con éxito', 'Aceptar', { duration: 3000 });
      });
    }
  }

  cancelar(): void {
    this.formularioVisible = false;
  }

  eliminarProyecto(idProyecto: string): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '250px',
      data: { message: '¿Está seguro de que desea eliminar este proyecto?' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.proyectoService.eliminarProyecto(idProyecto).subscribe(() => {
          this.cargarProyectos();
          this.snackBar.open('Proyecto eliminado con éxito', 'Aceptar', { duration: 3000 });
        });
      }
    });
  }

  verDetalles(proyecto: any): void {
    this.mostrarFormulario('verDetalles', proyecto);
  }
}
