import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Inventory } from 'src/app/Models/inventory';
import { InventoryService } from 'src/app/service/inventory.service';

@Component({
  selector: 'app-search-inventory',
  templateUrl: './search-inventory.component.html',
  styleUrls: ['./search-inventory.component.css']
})
export class SearchInventoryComponent {
  inventoryId: number = 0;
  inventory: Inventory | undefined;

  constructor(private inventoryService: InventoryService) { }

  searchInventory() {
    this.inventoryService.searchInventory(this.inventoryId)
      .subscribe(response => {
        this.inventory = response;
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      });
  }

}
