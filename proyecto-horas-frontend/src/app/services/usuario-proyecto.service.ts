import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import baserUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class UsuarioProyectoService {

  constructor(private http: HttpClient) { }

  
  public obtenerProyectosPorUsuario(usuarioId: number): Observable<any> {
    return this.http.get(`${baserUrl}/usuario-proyectos/usuario/${usuarioId}`);
  }

  public asignarProyecto(asignacion: any): Observable<any> {
    return this.http.post(`${baserUrl}/usuario-proyectos/`, asignacion);
  }
}
