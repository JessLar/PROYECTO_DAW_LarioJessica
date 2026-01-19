import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDrawer } from '@angular/material/sidenav';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  @ViewChild('drawer') drawer!: MatDrawer;

  isLoggedIn = false;
  user: any = null;
  userRole: string = '';

  constructor(private loginService: LoginService, private router: Router) {}

  ngOnInit(): void {
    this.updateUserState();
    this.loginService.loginStatusSubjec.asObservable().subscribe(() => {
      this.updateUserState();
    });
  }

  updateUserState(): void {
    this.isLoggedIn = this.loginService.isLoggedIn();
    this.user = this.loginService.getUser();
    if (this.user) {
      this.userRole = this.loginService.getUserRole();
    }
  }

  logout(): void {
    this.loginService.logout();
    this.updateUserState();
    this.router.navigate(['login']);
  }

  toggleMenu(): void {
    this.drawer.toggle();
  }
  getUserImage() {
  const user = this.loginService.getUser();

  // Si el backend ya envía la URL completa
  if (user.imagenPerfil.startsWith('http')) {
    return user.imagenPerfil;
  }

  // Si solo envía el nombre del archivo
  return 'http://localhost:8080/images/' + user.imagenPerfil;
}

}
