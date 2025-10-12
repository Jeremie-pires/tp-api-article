import { Component } from '@angular/core';
import { Article } from '../../../Models/article';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-article-list',
  imports: [RouterModule],
  templateUrl: './article-list.html',
  standalone: true
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
          next: (response: any) => {
            console.log('Response displayArticles:', response);
            if (response.code === 200) {
              this.articles = response.data;
            } else {
              console.error('Erreur lors de la récupération des articles:', response.message);
            }
          },
          error: (error) => {
            console.error('Error displayArticles:', error);
          }
        }
      )
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/']);
  }
}