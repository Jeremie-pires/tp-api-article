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
  standalone: true
})
export class Signup {
  user: User = new User();
  errorMessage: string = '';

  constructor(
    private readonly httpClient: HttpClient,
    private readonly router: Router
  ) {}

  signup() {
    this.errorMessage = '';

    const url = 'http://localhost:8080/users/signup';
    
    this.httpClient.post(url, this.user).subscribe({
      next: (response: any) => {
        console.log('Response signup:', response);
        if (response.code === 200) {
          this.router.navigate(['/login']);
        } else {
          this.errorMessage = response.message || 'Erreur lors de l\'inscription';
        }
      },
      error: (error) => {
        console.error('Error signup:', error);
        this.errorMessage = 'Erreur lors de l\'inscription';
      }
    });
  }
}
