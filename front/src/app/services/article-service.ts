import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Article } from '../../Models/article';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  public data: any;

  constructor(private httpClient: HttpClient) {}

    loadArticle(_id: string): Observable<Article> {
      const url = `http://localhost:8080/articles/${_id}`;
      
      return this.httpClient.get(url).pipe(
        map((response: any) => {
          console.log('Response loadArticle:', response);
          if (response.code === 200) {
            this.data = Object.assign(new Article(), response.data);
            return this.data;
          } else {
            throw new Error(response.message || 'Erreur lors du chargement de l\'article');
          }
        })
      );
    }
}
