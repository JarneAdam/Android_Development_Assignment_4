package be.vives.jarne.assignment_4.ui

import androidx.lifecycle.ViewModel
import be.vives.jarne.assignment_4.models.MockupToDo
import be.vives.jarne.assignment_4.models.ToDo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ToDetailUiState(
    val toDo: ToDo? = null,
)

class ToDetailViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ToDetailUiState(toDo = MockupToDo.getToDos().first()))
    val uiState: StateFlow<ToDetailUiState> = _uiState.asStateFlow()

    fun setToDo(toDo: ToDo?) {
        if (toDo != null) {
            _uiState.update { it.copy(toDo = toDo) }
        }
    }
}
