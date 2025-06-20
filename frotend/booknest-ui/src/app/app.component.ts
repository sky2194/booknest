import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
//import { HttpClientModule } from '@angular/common/http';

import { BookAddComponent } from './components/book-add/book-add.component';
import { BookListComponent } from './components/book-list/book-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,             // ✅ changed
    FormsModule,
    BookAddComponent,
    BookListComponent
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'booknest-ui';
}