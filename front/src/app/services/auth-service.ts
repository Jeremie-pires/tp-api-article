import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  errorMessage: string | undefined;
  user: any;
}
