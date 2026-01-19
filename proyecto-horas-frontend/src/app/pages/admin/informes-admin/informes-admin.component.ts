import { Component, OnInit } from '@angular/core';
import { ProyectoService } from '../../../services/proyecto.service';
import { HorasService } from '../../../services/horas.service';
import { UsuarioService } from '../../../services/usuario.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UsuarioProyectoService } from '../../../services/usuario-proyecto.service';

@Component({
  selector: 'app-informes-admin',
  templateUrl: './informes-admin.component.html',
  styleUrls: ['./informes-admin.component.css']
})
export class InformesAdminComponent implements OnInit {
  usuarios: any[] = [];
  proyectos: any[] = [];
  informes: any[] = [];
  selectedUsuario: number | undefined;
  selectedProyecto: string = '';
  selectedMes: number | undefined;
  displayedColumns: string[] = ['proyecto', 'fecha', 'tipo', 'horas', 'totalHoras'];
  totalSumHoras: number = 0;
  meses: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];

  constructor(
    private horasService: HorasService,
    private proyectoService: ProyectoService,
    private usuarioService: UsuarioService,
    private snackBar: MatSnackBar, 
    private usuarioProyectoService: UsuarioProyectoService
  ) {}

  ngOnInit(): void {
    this.usuarioService.obtenerUsuarios().subscribe(
      (usuarios: any[]) => {
        this.usuarios = usuarios;
      },
      error => {
        this.snackBar.open('Error al obtener usuarios', 'Cerrar', {
          duration: 3000
        });
      }
    );

    this.proyectoService.obtenerProyectos().subscribe(
      (proyectos: any[]) => {
        this.proyectos = proyectos;
      },
      error => {
        this.snackBar.open('Error al obtener proyectos', 'Cerrar', {
          duration: 3000
        });
      }
    );
  }

  cargarProyectos(): void {
    if (this.selectedUsuario) {
      this.usuarioProyectoService.obtenerProyectosPorUsuario(this.selectedUsuario).subscribe(
        (proyectos: any[]) => {
          this.proyectos = proyectos.map(proyecto => proyecto.proyecto);
        }
      );
    } else {
      this.proyectoService.obtenerProyectos().subscribe(
        (proyectos: any[]) => {
          this.proyectos = proyectos;
        }
      );
    }
  }

  obtenerInformes(): void {
    this.horasService.obtenerHorasPorFiltro(this.selectedUsuario, this.selectedProyecto, this.selectedMes).subscribe(
      (informes: any[]) => {
        this.informes = informes;
        this.calcularTotalSumHoras();
      },
      error => {
        this.snackBar.open('Error al obtener informes', 'Cerrar', {
          duration: 3000
        });
      }
    );
  }

  limpiarFormulario(): void {
    this.selectedUsuario = undefined;
    this.selectedProyecto = '';
    this.selectedMes = undefined;
    this.informes = [];
    this.totalSumHoras = 0;
  }

  calcularTotalSumHoras(): void {
    this.totalSumHoras = this.informes.reduce((total, informe) => total + informe.totalHoras, 0);
  }
}