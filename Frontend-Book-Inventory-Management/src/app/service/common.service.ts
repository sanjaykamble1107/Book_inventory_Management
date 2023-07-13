import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  catId: string='';

  setCategoryId(num:string){
    console.log("set category of common service class");
    this.catId=num;
  }

  constructor() { }
}
