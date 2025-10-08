import { Injectable } from '@angular/core';
import { User } from '../../Models/user';
import { Signup } from '../pages/signup/signup';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
    errorMessage: string | undefined;
  user: any;
    validateForm(): boolean {
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(this.user.email)) {
      this.errorMessage = 'Format d\'email invalide (ex: nom@domaine.com)';
      return false;
    }

    const cityCodePattern = /^\d{5}$/;
    if (!cityCodePattern.test(this.user.cityCode)) {
      this.errorMessage = 'Le code postal doit contenir exactement 5 chiffres';
      return false;
    }

    const phonePattern = /^\d{10}$/;
    if (!phonePattern.test(this.user.phone)) {
      this.errorMessage = 'Le numéro de téléphone doit contenir exactement 10 chiffres';
      return false;
    }

    return true;
  }
}
