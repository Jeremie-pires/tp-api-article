import { Component } from '@angular/core';
import { Article } from '../../../Models/article';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-article-list',
  imports: [HttpClientModule, RouterModule],
  templateUrl: './article-list.html',
  styleUrl: './article-list.scss'
})
export class ArticleList {

  articles : Article[] = []

  public constructor(
    private readonly httpClient: HttpClient,
    private readonly router: Router
  ) {
  }
  
  displayArticles() {
      const url = "http://localhost:8080/articles"
      this.httpClient.get(url).subscribe(
        {
          next: ({data}: any) => {
          this.articles = data;
          }
        }
      )
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/']);
  }
}