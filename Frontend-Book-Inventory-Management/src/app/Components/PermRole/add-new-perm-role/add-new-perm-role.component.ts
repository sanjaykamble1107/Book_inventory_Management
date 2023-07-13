import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { permRole } from 'src/app/Models/permrole';
import { PermRoleServiceService } from 'src/app/service/perm-role-service.service';

@Component({
  selector: 'app-add-new-perm-role',
  templateUrl: './add-new-perm-role.component.html',
  styleUrls: ['./add-new-perm-role.component.css']
})
export class AddNewPermRoleComponent implements OnInit {
  newPermRole: permRole = new permRole(); // The new permRole object to add
  searchRoleNumber: number = 0; // The role number for searching permRole
  updateRoleNumber: number = 0; // The role number for updating permRole
  updatedPermRole: string = ''; // The updated permRole value
  searchResults: permRole[] = []; // Array to store search results

  constructor(private permRoleService: PermRoleServiceService) { }

  ngOnInit(): void {
    // Initialization logic goes here
  }

  // Function to add a new permRole
  addPermRole() {
    this.permRoleService.addPermRole(this.newPermRole).subscribe(
      () => {
        // Clear the form
        this.newPermRole = new permRole();
        console.log('PermRole added successfully');
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }

  // Function to search permRole by role number
  searchPermRole() {
    this.permRoleService.searchPermRole(this.searchRoleNumber).subscribe(
      (permRole) => {
        console.log('Search result:', permRole);
        this.searchResults = [permRole];
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }

  // Function to update permRole by role number
  updatePermRole() {
    this.permRoleService.updatePermRole(this.updateRoleNumber, this.updatedPermRole).subscribe(
      () => {
        // Clear the form
        this.updateRoleNumber = 0;
        this.updatedPermRole = '';
        console.log('PermRole updated successfully');
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }
}
