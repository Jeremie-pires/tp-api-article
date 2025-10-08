import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleInfos } from './article-infos';

describe('ArticleInfos', () => {
  let component: ArticleInfos;
  let fixture: ComponentFixture<ArticleInfos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArticleInfos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticleInfos);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
