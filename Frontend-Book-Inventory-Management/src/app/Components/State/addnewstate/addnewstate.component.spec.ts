import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddnewstateComponent } from './addnewstate.component';

describe('AddnewstateComponent', () => {
  let component: AddnewstateComponent;
  let fixture: ComponentFixture<AddnewstateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddnewstateComponent]
    });
    fixture = TestBed.createComponent(AddnewstateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
