import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baserUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class ProyectoService {

  constructor(private http: HttpClient) { }

  public obtenerProyectos() {
    return this.http.get<any[]>(`${baserUrl}/proyectos/`);
  }
  public agregarProyecto(proyecto: any) {
    return this.http.post(`${baserUrl}/proyectos/`, proyecto);
  }

  public editarProyecto(proyecto: any) {
    return this.http.put(`${baserUrl}/proyectos/`, proyecto);
  }

  public eliminarProyecto(proyectoId: string) {
    return this.http.delete(`${baserUrl}/proyectos/${proyectoId}`);
  }
}
