import { Routes } from '@angular/router';
import { ArticleForm } from './pages/article-form/article-form';
import { ArticleList } from './pages/article-list/article-list';
import { ArticleInfos } from './pages/article-infos/article-infos';
import { ForgotPassword } from './pages/forgot-password/forgot-password';
import { Login } from './pages/login/login';
import { Signup } from './pages/signup/signup';
import { Lobby } from './pages/lobby/lobby';

export const routes: Routes = [
    { path: '', component: Lobby },
    { path: 'lobby', component: Lobby },
    { path: 'article-form', component: ArticleForm },
    { path: 'article-form/:_id', component: ArticleForm },
    { path: 'article-list', component: ArticleList },
    { path: 'article-infos/:_id', component: ArticleInfos },
    { path: 'forgot-password', component: ForgotPassword },
    { path: 'login', component: Login },
    { path: 'signup', component: Signup }
];
