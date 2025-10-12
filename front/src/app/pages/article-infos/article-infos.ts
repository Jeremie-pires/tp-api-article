import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Article } from '../../../Models/article';
import { ArticleService } from '../../services/article-service';

@Component({
  selector: 'app-article-infos',
  imports: [RouterModule],
  providers: [ArticleService],
  templateUrl: './article-infos.html',
  standalone: true
})
export class ArticleInfos implements OnInit {

  article: Article = new Article();
  articleId: string | null = null;

  constructor(
    private readonly httpClient: HttpClient,
    private readonly route: ActivatedRoute,
    private readonly router: Router,
    public readonly articleService: ArticleService
  ) {}

  ngOnInit() {
    this.articleId = this.route.snapshot.paramMap.get('_id');
    
    if (this.articleId) {
      this.articleService.loadArticle(this.articleId).subscribe(
        article => this.article = article
      );
    }
  }


  deleteArticle() {
    if (this.articleId) {
      const url = `http://localhost:8080/articles/${this.articleId}`;
      
      this.httpClient.delete(url).subscribe({
        next: (response: any) => {
          console.log('Response deleteArticle:', response);
          if (response.code === 200) {
            this.router.navigate(['/article-list']);
          } else {
            console.error('Erreur lors de la suppression:', response.message);
          }
        },
        error: (error) => {
          console.error('Error deleteArticle:', error);
        }
      });
    }
  }

}
