import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Article } from '../../../Models/article';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ArticleService } from '../../services/article-service';

@Component({
  selector: 'app-article-form',
  imports: [RouterModule, FormsModule, CommonModule],
  providers: [ArticleService],
  templateUrl: './article-form.html',
  styleUrl: './article-form.scss'
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

  saveArticle() {
    this.errorMessage = '';
    
    if (!this.validateForm()) {
      return;
    }

    const url = 'http://localhost:8080/articles/save';

 
    this.httpClient.post(url, this.article).subscribe({
      next: ({ data }: any) => {
        this.router.navigate(['/article-list']);
      }
    });
  }

    validateForm(): boolean {
      if (!this.article.title || !this.article.desc || !this.article.author || !this.article.imgPath) {
        this.errorMessage = 'Tous les champs sont obligatoires.';
        return false;
      }
    
      if (this.article.imgPath && !this.isValidUrl(this.article.imgPath)) {
        this.errorMessage = 'L\'URL de l\'image n\'est pas valide.';
        return false;
      }
    
      return true;
    }

  private isValidUrl(url: string): boolean {
  const urlPattern = /^(https?|ftp):\/\/[^\s/$.?#].[^\s]*$/i;
  return urlPattern.test(url);
}
}
