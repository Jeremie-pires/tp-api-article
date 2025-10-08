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
        map(({ data }: any) => {
          this.data = Object.assign(new Article(), data);
          return this.data;
        })
      );
    }
}
