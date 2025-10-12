import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-forgot-password',
  imports: [RouterModule, FormsModule, CommonModule],
  templateUrl: './forgot-password.html',
  standalone: true
})
export class ForgotPassword {
  email: string = '';
  message: string = '';
  isSuccess: boolean = false;
  newPassword: string = '';
  copied: boolean = false;

  constructor(
    private readonly httpClient: HttpClient,
    private readonly router: Router
  ) {}

  resetPassword() {
    const url = 'http://localhost:8080/users/reset-password';
    this.message = '';
    
    this.httpClient.post(url, { email: this.email }).subscribe({
      next: (response: any) => {
        console.log('Response resetPassword:', response);
        if (response.code === 200) {
          this.isSuccess = true;
          this.newPassword = response.data;
          this.message = response.message || 'Votre nouveau mot de passe a été généré avec succès !';
        } else {
          this.isSuccess = false;
          this.message = response.message || 'Erreur lors de la réinitialisation du mot de passe';
        }
      },
      error: (error) => {
        console.error('Error resetPassword:', error);
        this.isSuccess = false;
        this.message = 'Erreur lors de la réinitialisation du mot de passe';
      }
    });
  }

  copyPassword() {
    // Copier le mot de passe dans le presse-papier
    navigator.clipboard.writeText(this.newPassword).then(() => {
      this.copied = true;
      // Réinitialiser le message après 3 secondes
      setTimeout(() => {
        this.copied = false;
      }, 3000);
    }).catch(err => {
      console.error('Erreur lors de la copie:', err);
    });
  }
}
