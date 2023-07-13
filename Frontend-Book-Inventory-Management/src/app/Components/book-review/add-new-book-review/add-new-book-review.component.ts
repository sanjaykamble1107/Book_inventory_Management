import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { BookReviewService } from 'src/app/service/book-review.service';
import { bookReview } from '../bookreview';

@Component({
  selector: 'app-add-new-book-review',
  templateUrl: './add-new-book-review.component.html',
  styleUrls: ['./add-new-book-review.component.css']
})
export class AddNewBookReviewComponent {
  bookReview: bookReview = new bookReview();
  ratingInvalid: boolean = false;

  constructor(private bookReviewService: BookReviewService) { }

  addBookReview() {
    if (this.bookReview.rating < 0 || this.bookReview.rating > 10) {
      this.ratingInvalid = true;
      return;
    }

    this.bookReviewService.addBookReview(this.bookReview)
      .subscribe(
        response => {
          console.log('Book review added successfully', response);
          // Show success message or perform other actions
          this.resetForm();
        }, (error: HttpErrorResponse) => {

          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
        }
      );
  }
  resetForm() {
    this.bookReview = new bookReview();
    this.ratingInvalid = false;
  }
}
