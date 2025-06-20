export interface Book {
    id?: number;         // Optional when creating a new book
    title: string;
    author: string;
    price: number;
    stock: number;
  }