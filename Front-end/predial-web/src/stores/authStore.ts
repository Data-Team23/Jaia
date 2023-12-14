import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useAuthStore = defineStore('auth', () => {
  const showOnlyRequisicoes = ref(false);
  const userId = ref(null);

  const persistedState = localStorage.getItem('authStoreState');
  if (persistedState) {
    const parsedState = JSON.parse(persistedState);
    showOnlyRequisicoes.value = parsedState.showOnlyRequisicoes;
    userId.value = parsedState.userId;
  }

  function toggleRequisicoesVisibility() {
    showOnlyRequisicoes.value = !showOnlyRequisicoes.value;
    saveStateToLocalStorage();
  }

  function setUserId(id: null) {
    userId.value = id;
    saveStateToLocalStorage();
  }

  function saveStateToLocalStorage() {
    const stateToSave = {
      showOnlyRequisicoes: showOnlyRequisicoes.value,
      userId: userId.value,
    };
    localStorage.setItem('authStoreState', JSON.stringify(stateToSave));
  }

  return { showOnlyRequisicoes, userId, toggleRequisicoesVisibility, setUserId };
});
