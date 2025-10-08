import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import { User } from '../../../Models/user';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-signup',
  imports: [RouterModule, FormsModule, CommonModule],
  providers: [AuthService],
  templateUrl: './signup.html',
  styleUrl: './signup.scss'
})
export class Signup {
  user: User = new User();
  passwordConfirm: string = '';
  errorMessage: string = '';

  constructor(
    private readonly httpClient: HttpClient,
    private readonly router: Router
  ) {}

  signup() {
    this.errorMessage = '';
    
    if (!this.validateForm()) {
      return;
    }

    const url = 'http://localhost:8080/signup';
    
    const signupData = {
      ...this.user,
      passwordConfirm: this.passwordConfirm
    };
    
    this.httpClient.post(url, signupData).subscribe({
      next: (response: any) => {
        if (response.code === "200") {
          this.router.navigate(['/login']);
        } else {
          this.errorMessage = response.message || 'Erreur lors de l\'inscription';
        }
      }
    });
  }
  validateForm(): boolean {
    if (!this.user.pseudo || !this.user.email || !this.user.password) {
      this.errorMessage = 'Tous les champs sont obligatoires.';
      return false;
    }
    if (this.user.password !== this.passwordConfirm) {
      this.errorMessage = 'Les mots de passe ne correspondent pas.';
      return false;
    }
    return true;
  }
}
