import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { DashboardComponent } from './pages/admin/dashboard/dashboard.component';
import { UserDashboardComponent } from './pages/user/user-dashboard/user-dashboard.component';

import { AdminGuard } from './services/admin.guard';
import { NormalGuard } from './services/normal.guard';
import { RegistroHorasComponent } from './pages/user/registro-horas/registro-horas.component';
import { InformesComponent } from './pages/user/informes/informes.component';
import { InformesAdminComponent } from './pages/admin/informes-admin/informes-admin.component';
import { UsuariosComponent } from './pages/admin/usuarios/usuarios.component';
import { ProyectosComponent } from './pages/admin/proyectos/proyectos.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path: 'admin',
    component: DashboardComponent,
    canActivate: [AdminGuard],
  },
 { 
    path: 'usuarios', 
    component: UsuariosComponent 
  },
  { 
    path: 'proyectos', 
    component: ProyectosComponent 
  },
  { 
    path: 'informes-admin', 
    component: InformesAdminComponent 
  },
  {
    path: 'user-dashboard',
    component: UserDashboardComponent,
    pathMatch: 'full',
    canActivate: [NormalGuard]
  },
  {
    path: 'registro-horas',
    component: RegistroHorasComponent,
    canActivate: [NormalGuard]
  },
  {
    path: 'informes',
    component: InformesComponent,
    canActivate: [NormalGuard]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }