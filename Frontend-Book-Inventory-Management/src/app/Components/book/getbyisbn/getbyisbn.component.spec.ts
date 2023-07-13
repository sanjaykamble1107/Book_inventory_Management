import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetbyisbnComponent } from './getbyisbn.component';

describe('GetbyisbnComponent', () => {
  let component: GetbyisbnComponent;
  let fixture: ComponentFixture<GetbyisbnComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetbyisbnComponent]
    });
    fixture = TestBed.createComponent(GetbyisbnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
