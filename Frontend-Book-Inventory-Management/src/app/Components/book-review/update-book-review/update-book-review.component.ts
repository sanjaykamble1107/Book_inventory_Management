import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/Models/book';
import { BookReviewService } from 'src/app/service/book-review.service';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-update-book-review',
  templateUrl: './update-book-review.component.html',
  styleUrls: ['./update-book-review.component.css'],
})
export class UpdateBookReviewComponent implements OnInit {
  isbn: string = '';
  updateField: string = 'rating';
  newRating: number = 0;
  newComments: string = '';
  isUpdateSuccessful: boolean = false;
  updateError: boolean = false;
  bookList: Book[] = [];

  constructor(
    private bookReviewService: BookReviewService,
    private bookService: BookService
  ) { }
  ngOnInit(): void {
    this.bookService.getAllBooks().subscribe(
      (res: any) => {
        this.bookList = res;
      },
      (error: HttpErrorResponse) => {
        let errorMessage = error.error.errorMessage;
        console.log(errorMessage);
      }
    );
  }

  updateBookReview() {
    this.isUpdateSuccessful = false;
    this.updateError = false;

    if (this.updateField === 'rating') {
      this.bookReviewService
        .updateRatingByISBN(this.isbn, this.newRating)
        .subscribe(
          () => {
            console.log('Book review rating updated successfully');
            this.isUpdateSuccessful = true;
            this.clearForm();
          },
          (error: HttpErrorResponse) => {
            const errorMessage = error.error.errorMessage;
            alert(errorMessage);
          }
        );
    } else if (this.updateField === 'comments') {
      this.bookReviewService
        .updateCommentsByISBN(this.isbn, this.newComments)
        .subscribe(
          () => {
            console.log('Book review comments updated successfully');
            this.isUpdateSuccessful = true;
            this.clearForm();
          },
          (error: HttpErrorResponse) => {
            const errorMessage = error.error.errorMessage;
            alert(errorMessage);
          }
        );
    }
  }

  clearForm() {
    this.isbn = '';
    this.newRating = 0;
    this.newComments = '';
  }
}
