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
  articleService: any;

  constructor(
    private readonly httpClient: HttpClient,
    private readonly route: ActivatedRoute,
    private readonly router: Router
  ) {}

  ngOnInit() {
    this.articleId = this.route.snapshot.paramMap.get('_id');
    
    if (this.articleId) {
      this.isEditMode = true;
      this.articleService.loadArticle(this.articleId);
    }
  }

  saveArticle() {
    const url = 'http://localhost:8080/articles/save';
    
    this.httpClient.post(url, this.article).subscribe({
      next: ({ data }: any) => {
        this.router.navigate(['/article-list']);
      }
    });
  }
}
