import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';    // ✅ Add this

@Component({
  selector: 'app-book-list',
  standalone: true,
  imports: [CommonModule],                         // ✅ Replace BrowserModule
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent {
  // your logic
}