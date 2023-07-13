import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Reviewer } from 'src/app/Models/Reviewer';
import { ReviewerService } from 'src/app/service/reviewer.service';


@Component({
  selector: 'app-get-reviewer',
  templateUrl: './get-reviewer.component.html',
  styleUrls: ['./get-reviewer.component.css']
})
export class GetReviewerComponent {
  reviewerId: number = 0;
  reviewer: Reviewer | undefined;
  errorMessage: string | undefined;

  constructor(private reviewerService: ReviewerService) { }

  getReviewer() {
    this.reviewerService.getReviewerById(this.reviewerId)
      .subscribe(
        (response: Reviewer) => {
          this.reviewer = response;
          this.errorMessage = undefined; // Reset error message on success
        },
        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
        }
      );
  }
}
