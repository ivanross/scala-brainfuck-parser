package com.ivanross.brainfuck

trait RunnerMemory {

  var memory: Array[Byte]
  var ptr: Int = 0

  implicit def intToByte(x: Int): Byte = x.toByte

  def incrementPointer: Unit = ptr += 1
  def decrementPointer: Unit = ptr -= 1

  def incrementCell: Unit = writeMemory(readMemory + 1)
  def decrementCell: Unit = writeMemory(readMemory - 1)

  def readMemory: Byte = memory(ptr)
  def writeMemory(byte: Byte): Unit = memory(ptr) = byte

}
