import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewShoppingCartComponent } from './add-new-shopping-cart.component';

describe('AddNewShoppingCartComponent', () => {
  let component: AddNewShoppingCartComponent;
  let fixture: ComponentFixture<AddNewShoppingCartComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddNewShoppingCartComponent]
    });
    fixture = TestBed.createComponent(AddNewShoppingCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
