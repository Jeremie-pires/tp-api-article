import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-forgot-password',
  imports: [RouterModule, FormsModule, CommonModule],
  templateUrl: './forgot-password.html',
  styleUrl: './forgot-password.scss'
})
export class ForgotPassword {
  email: string = '';
  message: string = '';
  isSuccess: boolean = false;
  newPassword: string = '';

  constructor(
    private readonly httpClient: HttpClient,
    private readonly router: Router
  ) {}

  resetPassword() {
    const url = 'http://localhost:8080/reset-password';
    this.message = '';
    
    this.httpClient.post(url, { email: this.email }).subscribe({
      next: (response: any) => {
        if (response.code === "200") {
          this.isSuccess = true;
          this.newPassword = response.data;
          this.message = 'Votre nouveau mot de passe a été généré avec succès !';
        } else {
          this.isSuccess = false;
          this.message = 'Erreur lors de la réinitialisation du mot de passe';
        }
      }
    });
  }
}
