import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent {
  userId: number = 0;
  firstName: string = '';
  lastName: string = '';
  phoneNumber: number = 0;

  constructor(private userService: UserService) { }

  updateUser() {
    if (this.firstName) {
      this.userService.updateUserFirstName(this.userId, this.firstName)
        .subscribe(response => {
          alert('First name updated successfully');
          console.log('First name updated successfully');
        }, (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
        });
    }
    if (this.lastName) {
      this.userService.updateUserLastName(this.userId, this.lastName)
        .subscribe(response => {
          console.log('Last name updated successfully');
        }, (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
        });
    }
    if (this.phoneNumber) {
      this.userService.updateUserPhoneNumber(this.userId, this.phoneNumber)
        .subscribe(response => {
          console.log('Phone number updated successfully');
        }, (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
        });
    }
  }
}
