import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Article } from '../../../Models/article';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ArticleService } from '../../services/article-service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-article-form',
  imports: [RouterModule, FormsModule, CommonModule],
  providers: [ArticleService],
  templateUrl: './article-form.html',
  standalone: true
})
export class ArticleForm implements OnInit {

  article: Article = new Article();
  articleId: string | null = null;
  isEditMode: boolean = false;
  errorMessage: string = '';

  constructor(
    private readonly httpClient: HttpClient,
    private readonly route: ActivatedRoute,
    private readonly router: Router,
    public readonly articleService: ArticleService
  ) {}

  ngOnInit() {
    this.articleId = this.route.snapshot.paramMap.get('_id');
    
    if (this.articleId) {
      this.isEditMode = true;
      this.articleService.loadArticle(this.articleId).subscribe(
        article => this.article = article
      );
    }
  }

  updateArticle() {
    this.errorMessage = '';

    const url = `http://localhost:8080/articles/${this.articleId}`;

 
    this.httpClient.put(url, this.article).subscribe({
      next: (response: any) => {
        console.log('Response updateArticle:', response);
        if (response.code === 200) {
          this.router.navigate(['/article-list']);
        } else {
          this.errorMessage = response.message || 'Erreur lors de la mise à jour de l\'article';
        }
      },
      error: (error) => {
        console.error('Error updateArticle:', error);
        this.errorMessage = 'Erreur lors de la mise à jour de l\'article';
      }
    });
  }

  addArticle() {
    this.errorMessage = '';

    const url = 'http://localhost:8080/articles';
    
    this.httpClient.post(url, this.article).subscribe({
      next: (response: any) => {
        console.log('Response addArticle:', response);
        if (response.code === 200) {
          this.router.navigate(['/article-list']);
        } else {
          this.errorMessage = response.message || 'Erreur lors de l\'ajout de l\'article';
        }
      },
      error: (error) => {
        console.error('Error addArticle:', error);
        this.errorMessage = 'Erreur lors de l\'ajout de l\'article';
      }
    });
  }
}
