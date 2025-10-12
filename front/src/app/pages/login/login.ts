import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import { User } from '../../../Models/user';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [RouterModule, FormsModule, CommonModule],
  templateUrl: './login.html',
  standalone: true
})
export class Login {
  user: User = new User();
  errorMessage: string = '';

  constructor(
    private readonly httpClient: HttpClient,
    private readonly router: Router
  ) {}

  login() {
    const url = 'http://localhost:8080/users/login';
    this.errorMessage = '';
    
    this.httpClient.post(url, this.user).subscribe({
      next: (response: any) => {
        console.log('Response login:', response);
        if (response.code === 200) {
          localStorage.setItem('token', response.data);
          this.router.navigate(['/article-list']);
        } else {
          this.errorMessage = response.message || 'Email ou mot de passe incorrect';
        }
      },
      error: (error) => {
        console.error('Error login:', error);
        this.errorMessage = 'Erreur lors de la connexion';
      }
    });
  }
}
