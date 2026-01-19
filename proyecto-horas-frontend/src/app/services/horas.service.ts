import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baserUrl from './helper';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HorasService {

  constructor(private http: HttpClient) { }

  public guardarRegistro(registro: any): Observable<any> {
    return this.http.post(`${baserUrl}/horas-trabajadas/`, registro);
  }

  public actualizarRegistro(registro: any): Observable<any> {
    return this.http.put(`${baserUrl}/horas-trabajadas/`, registro);
  }

  public obtenerRegistros(): Observable<any[]> {
    return this.http.get<any[]>(`${baserUrl}/horas-trabajadas`);
  }

  public obtenerHorasPorUsuario(usuarioId: number): Observable<any[]> {
    return this.http.get<any[]>(`${baserUrl}/horas-trabajadas/usuario/${usuarioId}`);
  }

  public eliminarRegistro(id: number): Observable<any> {
    return this.http.delete(`${baserUrl}/horas-trabajadas/${id}`);
  }

  public obtenerHorasPorFiltro(usuarioId?: number, proyectoId?: string, mes?: number): Observable<any[]> {
    let url = `${baserUrl}/horas-trabajadas/filtro`;
    const params = [];
    
    if (usuarioId) {
      params.push(`usuarioId=${usuarioId}`);
    }
    if (proyectoId) {
      params.push(`proyectoId=${proyectoId}`);
    }
    if (mes !== undefined) {
      params.push(`mes=${mes}`);
    }
    if (params.length > 0) {
      url += `?${params.join('&')}`;
    }
    
    return this.http.get<any[]>(url);
  }
}
