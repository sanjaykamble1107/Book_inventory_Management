import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Reviewer } from 'src/app/Models/Reviewer';
import { ReviewerService } from 'src/app/service/reviewer.service';


@Component({
  selector: 'app-addnewreviewer',
  templateUrl: './addnewreviewer.component.html',
  styleUrls: ['./addnewreviewer.component.css']
})
export class AddnewreviewerComponent {

  newReviewer: Reviewer = {
    reviewerId: 0,
    name: '',
    employedBy: ''
  };

  constructor(private reviewerService: ReviewerService) { }

  addReviewer() {
    console.log(this.newReviewer);

    this.reviewerService.addReviewer(this.newReviewer).subscribe((res: any) => {
      console.log('New reviewer added:', res);
      // Reset the form
      alert(res.responseMessage);
      this.newReviewer = {
        reviewerId: 0,
        name: '',
        employedBy: ''
      };
    }, 
    (error: HttpErrorResponse) => {
      const errorMessage = error.error.errorMessage;
      
      alert(errorMessage);
      
    });
  }
}
