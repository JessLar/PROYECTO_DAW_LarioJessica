import { Component, OnInit } from '@angular/core';
import { UsuarioProyectoService } from '../../../services/usuario-proyecto.service';
import { HorasService } from '../../../services/horas.service';
import { LoginService } from '../../../services/login.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-informes',
  templateUrl: './informes.component.html',
  styleUrls: ['./informes.component.css']
})
export class InformesComponent implements OnInit {
  proyectos: any[] = [];
  selectedProyecto: string = '';
  selectedMes: number | undefined;
  informes: any[] = [];
  meses: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
  displayedColumns: string[] = ['proyecto', 'fecha', 'tipo', 'horas', 'totalHoras'];
  totalSumHoras: number = 0; 

  constructor(
    private horasService: HorasService,
    private usuarioProyectoService: UsuarioProyectoService,
    private loginService: LoginService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    const usuario = this.loginService.getUser();
    this.usuarioProyectoService.obtenerProyectosPorUsuario(usuario.id).subscribe((proyectos: any[]) => {
      this.proyectos = proyectos.map(proyecto => proyecto.proyecto);
    });
  }

  obtenerInformes(): void {
    const usuarioId = this.loginService.getUser().id;
    this.horasService.obtenerHorasPorFiltro(usuarioId, this.selectedProyecto, this.selectedMes).subscribe(
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

  limpiarFormulario() {
    this.selectedProyecto = '';
    this.selectedMes = undefined;
    this.informes = [];
    this.totalSumHoras = 0; 
  }

  calcularTotalSumHoras(): void {
    this.totalSumHoras = this.informes.reduce((total, informe) => total + informe.totalHoras, 0);
  }
}