import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { AuthorService } from 'src/app/service/author.service';
@Component({
  selector: 'app-update-author',
  templateUrl: './update-author.component.html',
  styleUrls: ['./update-author.component.css']
})
export class UpdateAuthorComponent {
  authorId: number=0;
  firstName: string='';
  lastName: string='';

  constructor(private authorService: AuthorService) {}

  updateFirstName() {
    this.authorService.updateFirstName(this.authorId, this.firstName)
      .subscribe(
        () => {
          // Update successful
          console.log('First Name updated successfully');
          alert('First Name updated successfully');
          // Reset form fields
          this.authorId = 0;
          this.firstName = '';
        },
        (error: HttpErrorResponse) => {
        
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
        }
      );
  }

  updateLastName() {
    this.authorService.updateLastName(this.authorId, this.lastName)
      .subscribe(
        () => {
          // Update successful
          console.log('Last Name updated successfully');
          alert('Last Name updated successfully');
          // Reset form fields
          this.authorId = 0;
          this.lastName = '';
        },
        (error: HttpErrorResponse) => {
        
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
        }
      );
  }
}
