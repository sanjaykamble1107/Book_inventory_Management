import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { User } from 'src/app/Models/user';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-get-user',
  templateUrl: './get-user.component.html',
  styleUrls: ['./get-user.component.css']
})
export class GetUserComponent {
  userList: User[] = [];
  searchUserId: number = 0;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    // this.getAllUsers();
  }

  searchUsers() {
    if (this.searchUserId) {
      this.userService.getUserById(this.searchUserId).subscribe((res: User) => {
        if (res) {
          this.userList = [res]; // Wrap the response in an array
        } else {
          this.userList = []; // Clear the user list if no user found
        }
      },
      (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
      });
    }
  }
}
