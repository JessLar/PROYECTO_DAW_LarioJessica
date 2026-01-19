import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {
  user: any = null;

  constructor(private loginService: LoginService, private router: Router) {}

  ngOnInit(): void {
    
  }

  goToRegistroHoras() {
    this.router.navigate(['registro-horas']);
  }

  goToInformes() {
    this.router.navigate(['informes']);
  }
}