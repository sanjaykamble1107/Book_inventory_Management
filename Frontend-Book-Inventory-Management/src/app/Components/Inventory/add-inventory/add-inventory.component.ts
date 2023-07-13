import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/Models/book';
import { Inventory } from 'src/app/Models/inventory';
import { BookService } from 'src/app/service/book.service';
import { InventoryService } from 'src/app/service/inventory.service';

@Component({
  selector: 'app-add-inventory',
  templateUrl: './add-inventory.component.html',
  styleUrls: ['./add-inventory.component.css']
})
export class AddInventoryComponent implements OnInit {

  inventory: Inventory;
  bookList:Book[]=[];

  constructor(private inventoryService: InventoryService,private bookService:BookService) {
    this.inventory = new Inventory();
  }
  ngOnInit(): void {
    this.bookService.getAllBooks().subscribe(
      (res: any) => {
        this.bookList = res;
      },
      (error: HttpErrorResponse) => {
        let errorMessage = error.error.errorMessage;
        console.log(errorMessage);
      }
    );
  }

  addInventory() {
    this.inventory.inventoryId=-1;
    this.inventoryService.addInventory(this.inventory)
      .subscribe(response => {
        alert("Inventory Created Successfully");
        // To Reset the form
        this.inventory = new Inventory();
        console.log('Inventory added successfully' + response);
      }, 
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      });
  }
}

