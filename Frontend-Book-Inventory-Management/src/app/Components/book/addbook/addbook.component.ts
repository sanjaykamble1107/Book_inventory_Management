import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/Models/book';
import { Category } from 'src/app/Models/category';
import { Publisher } from 'src/app/Models/publisher';
import { BookService } from 'src/app/service/book.service';
import { CategoryService } from 'src/app/service/category.service';
import { PublisherService } from 'src/app/service/publisher.service';

@Component({
  selector: 'app-addbook',
  templateUrl: './addbook.component.html',
  styleUrls: ['./addbook.component.css'],
})
export class AddbookComponent implements OnInit {
  book: Book = new Book(); // creating book object

  constructor(
    private bookService: BookService,
    private router: Router,
    private categoryService: CategoryService,
    private publisherService: PublisherService
  ) { }
  categoryList: Category[] = [];
  publisherList: Publisher[] = [];

  ngOnInit(): void {
    this.categoryService.getAllCategories().subscribe(
      (res: any) => {
        this.categoryList = res;
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );

    
    this.publisherService.getAllPublisher().subscribe(
      (res: any) => {
        this.publisherList = res;
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }

  addbook() {
    console.log(this.book);
    this.bookService.createBook(this.book).subscribe(
      () => {
        // Update successful
        console.log('Book added successfully');
        alert('Book added successfully');
        // Reset form fields
        this.book = new Book();
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }
}
