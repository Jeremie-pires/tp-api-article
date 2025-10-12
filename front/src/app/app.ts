import { Component, signal } from '@angular/core';
import { RouterOutlet, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  standalone: true
})
export class App {
  protected readonly title = signal('article-app');

  constructor(private router: Router) {}
}
