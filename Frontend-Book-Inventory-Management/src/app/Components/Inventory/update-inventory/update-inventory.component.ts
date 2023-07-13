import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { InventoryService } from 'src/app/service/inventory.service';

@Component({
  selector: 'app-update-inventory',
  templateUrl: './update-inventory.component.html',
  styleUrls: ['./update-inventory.component.css']
})
export class UpdateInventoryComponent {
  inventoryId: number = 0; // The ID of the inventory item to update
  purchased: number | string = 0; // The updated value for the "purchased" field

  constructor(private inventoryService: InventoryService) { }

  updateInventory() {
    if (typeof this.purchased === 'string') {
      this.purchased = parseInt(this.purchased); // Convert the "purchased" value to a number if it is currently a string
    }
    if (this.purchased !== 0 && this.purchased !== 1) {
      console.error('Invalid value for purchased field'); // Log an error if the "purchased" value is neither 0 nor 1
      return;
    }

    this.inventoryService.updatePurchased(this.inventoryId, this.purchased)
      .subscribe(
        response => {
          alert('inventory updated successfully'); // Show a success message to the user
          console.log('Inventory updated successfully'); // Log a success message to the console
          // Reset the form
          this.inventoryId = 0;
          this.purchased = 0;
        },
        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage; // Retrieve the error message from the HTTP response
          alert(errorMessage); // Show the error message to the user
        }
      );
  }
}
