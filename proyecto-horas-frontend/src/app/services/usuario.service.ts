// usuario.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import baserUrl from './helper';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) { }

  public obtenerUsuarios(): Observable<any> {
    return this.http.get(`${baserUrl}/usuarios/`);
  }

  public agregarUsuario(formData: FormData) {
    return this.http.post(`${baserUrl}/usuarios/`, formData);
  }
editarUsuario(usuarioId: number, formData: FormData) {
  return this.http.put(`${baserUrl}/usuarios/${usuarioId}`, formData);
}



  public eliminarUsuario(usuarioId: number) {
    return this.http.delete(`${baserUrl}/usuarios/${usuarioId}`);
  }

  public obtenerRoles(): Observable<any> {
    return this.http.get(`${baserUrl}/roles/`);
  }
}
