import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { ReviewerService } from 'src/app/service/reviewer.service';

@Component({
  selector: 'app-update-reviewer',
  templateUrl: './update-reviewer.component.html',
  styleUrls: ['./update-reviewer.component.css']
})
export class UpdateReviewerComponent {
  reviewerId: number = 0;
  name: string = '';
  employedBy: string = '';

  constructor(private reviewerService: ReviewerService) { }

  updateReviewer() {
    if (this.name) {
      this.reviewerService.updateReviewerName(this.reviewerId, this.name)
        .subscribe(response => {
          console.log('Name updated successfully');
          alert("Reviewer updated successfully");
        },  (error: HttpErrorResponse) => {

          const errorMessage = error.error.errorMessage;
  
          alert(errorMessage);
  
        }
        );
    }
    if (this.employedBy) {
      this.reviewerService.updateReviewerEmployedBy(this.reviewerId, this.employedBy)
        .subscribe(response => {
          console.log('Employed by updated successfully');
          alert("Reviewer updated successfully");
        },  (error: HttpErrorResponse) => {

          const errorMessage = error.error.errorMessage;
  
          alert(errorMessage);
  
        }
        );
    }
  }
}
