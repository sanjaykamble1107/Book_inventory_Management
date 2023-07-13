import { Component, OnInit } from '@angular/core';
import { PurchaseLogService } from 'src/app/service/purchase-log.service';
import { purchaseLog } from 'src/app/Models/purchselog';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-puchase-log',
  templateUrl: './add-puchase-log.component.html',
  styleUrls: ['./add-puchase-log.component.css']
})
export class AddPuchaseLogComponent implements OnInit {
  newPurchaseLog: purchaseLog = new purchaseLog();
  userIdToRetrieve: number = 0;
  purchaseLogs: purchaseLog[] = [];
  userIdToUpdate: number = 0;
  newInventoryId: number = 0;

  constructor(private purchaseLogService: PurchaseLogService) { }

  ngOnInit(): void {
    // this.getPurchaseLogs();
  }

  addPurchaseLog(): void {
    this.purchaseLogService.addPurchaseLog(this.newPurchaseLog).subscribe(() => {
      console.log("_________________" + this.newPurchaseLog)
      this.newPurchaseLog = new purchaseLog();
      this.getPurchaseLogs();
      alert("added")
    }, 
    (error: HttpErrorResponse) => {
      const errorMessage = error.error.errorMessage;
      alert(errorMessage);
    }
    );
  }

  getPurchaseLogs(): void {
    this.purchaseLogService.getPurchaseLogs().subscribe((logs) => {
      this.purchaseLogs = logs;
    }, 
    (error: HttpErrorResponse) => {
      const errorMessage = error.error.errorMessage;
      alert(errorMessage);
    }
    );
  }
}

