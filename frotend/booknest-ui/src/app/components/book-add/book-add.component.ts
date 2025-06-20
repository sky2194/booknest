import { Component } from '@angular/core';
import { Book } from '../../models/book';
import { BookService } from '../../services/book.service';

@Component({
  selector: 'app-book-add',
  templateUrl: './book-add.component.html',
  styleUrls: ['./book-add.component.css']
})
export class BookAddComponent {

  book: Book = {
    title: '',
    author: '',
    price: 0,
    stock: 0
  };

  constructor(private bookService: BookService) {}

  addBook(): void {
    this.bookService.addBook(this.book).subscribe(() => {
      alert('Book added successfully!');
      this.book = {
        title: '',
        author: '',
        price: 0,
        stock: 0
      };
    });
  }
}