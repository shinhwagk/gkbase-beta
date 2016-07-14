```typescript
export class KeyUpComponent_v1 {
  values = '';

  // without strong typing
  onKey(event:any) {
    this.values += event.target.value + ' | ';
  }
}
```

### 强类型版本
```typescript
export class KeyUpComponent_v1 {
  values = '';

  // with strong typing
  onKey(event: KeyboardEvent) {
    this.values += (<HTMLInputElement>event.target).value + ' | ';
  }
}
```

### 使用模板引用变量
```typescript
@Component({
  selector: 'loop-back',
  template: `
    <input #box (keyup)="0">
    <p>{{box.value}}</p>
  `
})
export class LoopbackComponent { }
```
